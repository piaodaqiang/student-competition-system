# 学生竞赛系统 - Swing版本

## 项目简介

这是一个基于Java Swing开发的学生竞赛系统，包含用户端和管理端两个模块。

## 功能模块

### 用户端
- 竞赛列表：浏览所有可用竞赛
- 我的参赛：查看用户的参赛记录
- 个人信息：查看用户基本信息

### 管理端
- 用户管理：管理系统用户
- 竞赛管理：发布和管理竞赛
- 成绩管理：录入和查询成绩
- 数据统计：查看系统统计数据

## 运行方式

### 方式一：使用Maven命令

1. 编译项目：
```bash
mvn clean compile
```

2. 运行应用：
```bash
mvn exec:java -Dexec.mainClass="com.example.studentcompetition.M1"
```

### 方式二：使用批处理脚本（Windows）

1. 首先编译项目：
```bash
mvn clean compile
```

2. 然后运行批处理脚本：
```bash
run.bat
```

## 登录信息

### 管理员账号
- 用户名：DGUT12306
- 密码：DGUT12306

### 普通用户
- 可以通过注册功能创建新账号
- 注册后的账号信息会保存在user_data.txt文件中

## 技术栈

- Java 17
- Swing GUI库
- Maven
- Gson（用于数据持久化）

## 项目结构

```
src/main/java/com/example/studentcompetition/
├── M1.java                # 主登录窗口
├── UserFrame.java         # 用户中心窗口
├── AdminFrame.java        # 管理员中心窗口
├── RegisterFrame.java     # 注册窗口
├── DataPersistence.java   # 数据持久化工具
└── entity/                # 实体类
    ├── CompetitionEntity.java    # 竞赛实体
    └── ParticipationEntity.java  # 参赛实体
```

## 注意事项

1. 应用程序运行后会显示登录窗口
2. 管理员账号是固定的，普通用户需要注册
3. 竞赛数据保存在competitions.json文件中
4. 参赛数据保存在participations.json文件中
5. 用户数据保存在user_data.txt文件中
6. 应用程序需要Java 17或更高版本运行

## 开发说明

本项目使用Maven进行依赖管理和构建，主要依赖包括：
- Spring Boot（基础框架）
- Gson（JSON处理）

## 许可证

MIT License