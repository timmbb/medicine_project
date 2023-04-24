import math
import pandas as pd
import numpy as np
import sys
# import os
#
# os.chdir('')



# 创建用户画像
# 参数说明：
# data_array: 所有用户对于其所看过的帖子的评分矩阵 data_array = [[2, 0, 0, 1.1, ...], [0, 0, 1.1, ...], ...]
# users_profiles = {user1:{'label1':1.1, 'label2': 0.5, 'label3': 0.0, ...}, user2:{...}...}
def createUsersProfiles(data_array,  items_names, labels_names, items_profiles):


    items_users_saw_scores = []
    items_users_saw = []
    count = 0
    score = 0.0
    for j in range(len(items_names)):
        items_users_saw.append(items_names[j])
        items_users_saw_scores.append([items_names[j], data_array[j]])
        count+=1
        score+=data_array[j]
    talk_average=score/count-1


    users_profiles = {}

    for j in range(len(labels_names)):
        count = 0
        score = 0.0
        for item in items_users_saw_scores:
            if items_profiles[item[0]][labels_names[j]] > 0:
                score += item[1]-talk_average
                count += 1
        # 如果求出的值太小，直接置0
        if abs(score) < 1e-6:
            score = 0.0
        if count==0:
            result=0.0
        else:
            result = score / count
        users_profiles[labels_names[j]] = result
    return users_profiles


# 创建帖子画像
# 参数说明：
# items_profiles = {item1:{'label1':1, 'label2': 0, 'label3': 0, ...}, item2:{...}...}
def createItemsProfiles(data_array, labels_names, items_names):
    items_profiles = {}

    for i in range(len(items_names)):

        items_profiles[items_names[i]] = {}

        for j in range(len(labels_names)):
            items_profiles[items_names[i]][labels_names[j]] = data_array[i][j]

    return items_profiles


# 计算用户画像向量与帖子画像向量的距离（相似度）
# 向量相似度计算公式：
# cos(user, item) = sigma_ui/sqrt(sigma_u * sigma_i)

# 参数说明：
# user_profile: 某一用户user的画像 user = {'label1':1.1, 'label2': 0.5, 'label3': 0.0, ...}
# item: 某一帖子item的画像 item = {'label1':1, 'label2': 0, 'label3': 0, ...}
# labels_names: 所有类型名
def calCosDistance(user, item, labels_names):
    sigma_ui = 0.0
    sigma_u = 0.0
    sigma_i = 0.0

    for label in labels_names:
        sigma_ui += user[label] * item[label]
        sigma_u += (user[label] * user[label])
        sigma_i += (item[label] * item[label])

    if sigma_u == 0.0 or sigma_i == 0.0:  # 若分母为0，相似度为0
        return 0

    return sigma_ui / math.sqrt(sigma_u * sigma_i)


# 基于内容的推荐算法：
# 借助特定某个用户user的画像user_profile和备选推荐帖子集的画像items_profiles，通过计算向量之间的相似度得出推荐帖子集

# 参数说明：
# user_profile: 某一用户user的画像 user_profile = {'label1':1.1, 'label2': 0.5, 'label3': 0.0, ...}
# items_profiles: 备选推荐帖子集的帖子画像: items_profiles = {item1:{'label1':1, 'label2': 0, 'label3': 0}, item2:{...}...}
# items_names: 备选推荐帖子集中的所有帖子名
# labels_names: 所有类型名
# items_user_saw: 用户user看过的帖子

def contentBased(user_profile, items_profiles, items_names, labels_names, items_user_saw):
    # 对于用户user的推荐帖子集为 recommend_items = [[帖子名, 该帖子画像与该用户画像的相似度], ...]
    recommend_items = []

    for i in range(len(items_names)):
        # 从备选推荐帖子集中的选择用户user没有看过的帖子
        if items_names[i] not in items_user_saw:
            recommend_items.append(
                [items_names[i], calCosDistance(user_profile, items_profiles[items_names[i]], labels_names)])
    # 将推荐帖子集按相似度降序排列
    recommend_items.sort(key=lambda item: item[1], reverse=True)

    return recommend_items


# 输出推荐给该用户的帖子列表
# max_num:最多输出的推荐帖子数
def printRecommendedItems(recommend_items_sorted, max_num):
    count = 0
    for item, degree in recommend_items_sorted:
        print("帖子名：%s， 推荐指数：%f" % (item, degree))
        count += 1
        if count == max_num:
            break


# 主程序
def recom(like_talk,collect_talk,all_talk):
    #类别
    all_labels = ['运动','饮食','医药','养生','心理']
    #点赞的帖子，你按下面这个格式来
    #like_talk=[{'title':'1','type':'运动'},{'title':'2','type':'运动'},{'title':'3','type':'心理'}]
    #收藏的帖子
    #collect_talk=[{'title':'1','type':'运动'},{'title':'2','type':'运动'}]
    user_talk=[]
    user_talk_score=[]
    for i in like_talk:
        # print(list(i.values())[0], type(i['tid']),i['tid'])
        user_talk.append(i['tid'])
    for i in collect_talk:
        if(i['tid'] not in user_talk):
            user_talk.append(i['tid'])
    for i in range(len(user_talk)):
        user_talk_score.append([0,0,0,0,0])
    for i in range(len(user_talk)):
        for j in like_talk:
            if user_talk[i]==j['tid']:
                user_talk_score[i][all_labels.index(j['ttype'])]+=1
        for j in collect_talk:
            if user_talk[i]==j['tid']:
                user_talk_score[i][all_labels.index(j['ttype'])]+=1
    item_user_talk_profiles=createItemsProfiles(user_talk_score,all_labels,user_talk)
    data_array=[]
    for i in user_talk_score:
        sum = 0
        for j in i:
            sum+=j
        data_array.append(sum)
    # # 建立用户画像users_profiles和用户看过的帖子集items_users_saw
    users_profiles= createUsersProfiles(data_array,  user_talk,
                                     all_labels, item_user_talk_profiles)
    #全部的帖子
    #all_talk=[{'title':'1','type':'运动'},{'title':'2','type':'运动'},{'title':'3','type':'心理'},
     #         {'title': '4', 'type': '运动'}, {'title': '5', 'type': '运动'}, {'title': '6', 'type': '心理'}]
    all_talk_name = []
    all_talk_score = []
    for i in all_talk:
        all_talk_name.append(i['tid'])
    for i in range(len(all_talk_name)):
        all_talk_score.append([0, 0, 0, 0, 0])
    for i in range(len(all_talk_name)):
        all_talk_score[i][all_labels.index(all_talk[i]['ttype'])] += 1
    items_to_be_recommended_profiles = createItemsProfiles(all_talk_score, all_labels, all_talk_name)
    recommend_items = contentBased(users_profiles, items_to_be_recommended_profiles,
                                   all_talk_name, all_labels, user_talk)
    recommend_items_str=''
    for i in range (len(recommend_items)):
        recommend_items_str+=str(recommend_items[i][0])
    for i in range(len(user_talk)):
        recommend_items_str+=user_talk[i]
    print(recommend_items_str)
    # print(user_talk)
    return recommend_items_str

if __name__ == '__main__':
    like_talk=eval(sys.argv[1])
    collect_talk=eval(sys.argv[2])
    all_talk=eval(sys.argv[3])
    #print(all_talk)
    recom(like_talk,collect_talk,all_talk)