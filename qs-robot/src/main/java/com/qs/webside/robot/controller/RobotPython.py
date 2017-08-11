#!/usr/bin/python
# -*- coding: UTF-8 -*-


import itchat, threading, requests, json
from flask import Flask
from flask import request
from multiprocessing import Process, Queue

"""消息类型常量"""
add_friend_request_type = 1 # 他人添加机器人为好友请求
add_friend_log_type = 2 # 请求把添加好友日志写到db

"""返回值常量"""
success = "success"
error = "error"
message = "message"
data = "data"

"""错误码比对表"""


def check_int(str):
    """检查字符串是否可以转int"""
    try:
        int(str)
        return True
    except:
        return False


postUrl = 'http://192.168.1.204:8080/app/api/robot/sendMsgToJava.html'


def send_post_request_to_java(type=0, data=None):
    """发送请求到java app后台
        返回值是一个dict
    """
    if data is None:
        data = {}
    send_data = {'type': type, 'data': json.dumps(data)}
    response = requests.post(postUrl, data=send_data)
    j = json.loads(response.text)
    return j


@itchat.msg_register(['Text', 'Map', 'Card', 'Note', 'Sharing'])
def text_reply(msg):# Note 通知类型，比如添加好友成功后，会收到好友界面的系统的通知:"你已经成功添加了xx为好友，现在可以开始聊天"
    print('Text', 'Map', 'Card', 'Note', 'Sharing ---::',msg['Type'], msg['Text'],msg)
    # itchat.send('%s: %s' % (msg['Type'], msg['Text']), msg['FromUserName'])


@itchat.msg_register('Friends')
def add_friend(msg):
    valify_msg = msg['Text']['autoUpdate']['Content']  # 验证消息格式：auth_code+mid
    li = valify_msg.split('+', 1)
    if len(li) == 2 and check_int(li[0]) and check_int(li[1]):
        robot_nickname = itchat.search_friends(userName=msg['ToUserName'])['NickName']
        d = {"authCode": str(li[0]), "mid": str(li[1]),"robName": robot_nickname}
        rd = send_post_request_to_java(type=add_friend_request_type, data=d) # result
        if int(rd[success]) == 1:# 成功
            pass
            #itchat.add_friend(userName=msg['RecommendInfo']['UserName'],status=3) # 同意加为好友
            #itchat.send_msg('Nice to meet you!', msg['RecommendInfo']['UserName'])
        else:
            print(int(rd[error]))


@itchat.msg_register('Text', isGroupChat=True)
def text_reply(msg):
    javaCallBackUrl = "http://192.168.1.204:8080/app/api/robot/openRoomViewUi.html"
    print(msg)
    try:
        qunzhuUserName = msg['User']['ChatRoomOwner']
    except:
        pass
    # d = send_post_request_to_java(type=1, data={'name1': 'value1', 'name2': 'value2'})
    # print(d['name1'])
    # qunzhuUserName = msg['User']['MemberList'][0]['UserName']
    # print(itchat.update_friend(userName=qunzhuUserName))
    # print(itchat.search_friends(userName=qunzhuUserName))
    # print(itchat.get_friends(update=True)) # 机器人更新好友列表
    # print(itchat.get_chatrooms(update=True)) # 机器人更新群列表
    # frindLists = itchat.get_friends(update=True)
    # itchat.set_alias(userName=qunzhuUserName, alias="Saywewe555416461646461646946164646163465461641646464646464676761") # 修改备注名称
    # frindLists = itchat.get_friends()
    # for frind in frindLists:
    #     if frind['UserName'] == qunzhuUserName:
    #         print(frind['RemarkName'])
    if str(msg['Content']) == 'aa':
        prefix = "请点击此链接完成待开房："
        print('robot send message by key word ::机器人开房')
        # itchat.send(u'@%s\u2005 %s' % (msg['ActualNickName'], prefix + javaCallBackUrl + '?fun=' + msg['FromUserName']),
        #             msg['FromUserName'])
    if msg['isAt']:
        # itchat.send(u'@%s\u2005I received: %s' % (msg['ActualNickName'], msg['Content']), msg['FromUserName'])
        if str(msg['Content']).find("开房") > 0:
            prefix = "请点击此链接完成待开房："
            print('robot send message by at me ::开房')
            # itchat.send(u'@%s\u2005 %s' % (msg['ActualNickName'], prefix + javaCallBackUrl + '?fun=' + msg['FromUserName']),msg['FromUserName'])


def itchatProcess():
    # itchat.auto_login(enableCmdQR=True, hotReload=True)
    itchat.auto_login(enableCmdQR=True)
    # itchat.auto_login(enableCmdQR=2, hotReload=True)linux下打开控制台扫描
    itchat.run()


"""web web web web web web web web web web web web web web web web web web web  应用开始"""
app = Flask(__name__)


@app.route('/', methods=['GET', 'POST'])
def home():
    print(itchat.search_friends())
    print(itchat.search_mps)
    print(itchat.search_chatrooms)
    return '<h1>Home</h1>'


# 客户端发送的是   content={json}类型的数据
@app.route('/sendOpenRoomResult.html', methods=['POST'])
def signin():
    immutableMultiDict = request.form
    v1 = immutableMultiDict.get('goldNum', default='')
    v2 = immutableMultiDict.get('sendType', default='')
    v3 = immutableMultiDict.get('signCode', default='')
    print(v1, v2, v3)
    # itchat.send_msg(msg="ddddddddddddd",toUserName="@@f799308a6aa2dd35c42d0f05258e4fc1b6bb98c5faf36140865ac6493b64a726")
    return ''


def webProcess():
    app.run(port=5555)

"""web web web web web web web web web web web web web web web web web web web  应用结束"""

if __name__ == '__main__':
    t1 = threading.Thread(target=itchatProcess, name='Thread-A')
    t2 = threading.Thread(target=webProcess, name='Thread-B')
    t1.start()
    t2.start()
    t1.join()
    t2.join()
