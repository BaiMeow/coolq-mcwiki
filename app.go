package main

import (
	"net/url"

	"github.com/Tnze/CoolQ-Golang-SDK/cqp"
	"github.com/Tnze/CoolQ-Golang-SDK/cqp/util"
)

//go:generate cqcfg -c .
// cqp: 名称: McWikiDirect
// cqp: 版本: 1.0.0:0
// cqp: 作者: MscBaiMeow
// cqp: 简介: 贼水的URL拼接
func main() { /*此处应当留空*/ }

func init() {
	cqp.AppID = "cn.miaoscraft.McWikiDirect" // TODO: 修改为这个插件的ID
	cqp.GroupMsg = onGroupMsg
	cqp.PrivateMsg = onPrivateMsg
}

func onPrivateMsg(subType, msgID int32, fromQQ int64, msg string, font int32) int32 {
	if msg[0:4] != "wiki " {
		return 0
	}
	cqp.SendPrivateMsg(fromQQ, util.Escape(getURL(msg)))
	return 0
}

func onGroupMsg(subType, msgID int32, fromGroup int64, fromQQ int64, fromAnonymous string, msg string, font int32) int32 {
	if msg[0:4] != "wiki " {
		return 0
	}
	cqp.SendGroupMsg(fromGroup, util.Escape(getURL(msg)))
	return 0
}

func getURL(keyword string) string {
	return url.QueryEscape("https://minecraft-zh.gamepedia.com/" + keyword)

}
