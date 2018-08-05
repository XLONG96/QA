# QA知识问答平台设计

## 主要技术
* 开发环境：IDEA
* 网页模板：JSP
* 框架：SpringMVC + Spring + Mybatis
* 验证和权限管理：Shiro
* 数据库：MySQL
* 项目管理：Maven
* 版本控制：Git
* 缓存：Ehcache
* 富文本：UEditor
* 前端：HTML + CSS + JavaScript
* 前端框架：JQuery + Bootstrap + FastJson

## 数据库设计
* 用户表

|id|username|sex|email|phone|profilePhoto|role|password|
|--|--|---|--|--|------------|--------|--|
|int|String|String|String|String|String|String|String|
|int|varchar()|varchar()|varchar()|varchar()|varchar()|varchar()|varchar()|


* 问题表

|id|detail|authorId|replyNum|starNum|publicTime|
|--|------|--------|--------|-------|----------|
|int|String|int|int|int|date|
|int|varchar()|int|int|int|date|

* 回答表

|id|detail|authorId|questionId|agreeNum|replyTime|
|--|------|--------|--------|-------|------|
|int|String|int|int|int|date|
|int|varchar()|int|int|int|date|

* 评论表

|id|detail|answererId|respondentId|replyId|agreeNum|commentTime|
|--|------|--------|--------|-------|--|--|
|int|String|int|int|int|int|date|
|int|varchar()|int|int|int|int|date|

* 问题关注表

|id|userId|questionId|StarTime|
|--|------|----------|--|
|int|int|int|date|
|int|int|int|date|

* 回答赞同表

|id|userId|replyId|agreeTime|
|--|------|-------|--|
|int|int|int|date|
|int|int|int|date|

* 评论赞同表

|id|userId|commentId|agreeTime|
|--|------|---------|--|
|int|int|int|date|
|int|int|int|date|

* 用户权限表

|id|userId|permisssion|
|--|------|------|
|int|int|String|
|int|int|varchar()|

## 用户行为分析

* **会员**
 - 查看问题
 - 提问（问题概要，问题详情，图片）
 - 回答问题（详情，图片）
 - 评论和删除评论
 - 点赞和取消赞
 - 关注问题和取消关注
 - 修改个人信息（用户名、密码、邮箱、手机、性别、头像）

* **管理员（Admin）**
 - 查看问题
 - 提问（问题概要，问题详情，图片）
 - 回答问题（详情，图片）
 - 评论（概要）
 - 点赞和取消赞
 - 关注问题和取消关注
 - 修改个人信息（用户名、密码、邮箱、手机、性别、头像）
 - 查看用户信息
 - 删除问题和评论
 - 封号