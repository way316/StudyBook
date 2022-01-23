# AWS

## IAM

Secret Access Key

只有在生成的时候可以看一次之后就再也看不到了，只能把Access Key给屏蔽了，然后再生成新的Access Key的时候看secret Access Key。

### Policy

- 用于给User或group提供权限的最小单位
- Jason format
- 每个policy包含：
  - Effecct：Allow/Deny
  - Action:list of actions
  - Resource:action的主体
  - Condition:实行条件



### Role

1. 允许访问AWS资源的帽子

2. 可以用于：

   - AWS service

   - Aws Account:
     - 需要制定允许给哪个account访问
     - 其他的Account需要有assume role权限
   - Web identity (Facebook或Google用户)
   - SAML(Security Assertion Markup Language) 2.0 federation

考试不考这一部分

### 考点

1. MFA(Multi-factor Authentication) 多重认证
   1. Virtual MFA device (Google Authenticator)
   2. Universal 2nd Factor Security Key (U2F)
   3. Hardware (秘钥)
   4. 

1. Root Account 和 Admin Account的区别：

   Root Account是一开始注册AWS账号的时候使用的account，拥有所有的权限，Admin Account是由Root Account创建的，权限是由Root Account给的

2. 新用户在分配Policy和Group之前没有任何权限

3. 新用户有两种，分别是前台和后台账户Management Console Access，Programmatic access( 可以共存)

4. Access Key/Secrete Access Key 用处：

   1. 不能用于前台登录但是可以用于API和Command Line
   2. Secrete Access Key只显示一次，如果丢失需要重新生成新的。

![image-20220118133750715](AWS.assets/image-20220118133750715.png)

### CLI and SDK

Command-line shell,命令行工具

SDK: AWS Software Development Kit(AWS SDK)

Cloud Shell: Command-line tool on Cloud

### IAM Security Tools

IAM Credentials Report (account-level): 会列出所有的user和他们的status.

IAM Access Advisor(user-Level): 显示user的service访问记录以及user的权限



### Summary

![image-20220122155641303](AWS.assets/image-20220122155641303.png)

## S3 Simple Storage Service

### 特点

1. Object Based (对象基础的,区别于Blcoked Based存储的，比如数据库)

2. 可以存储0-5TB

3. 总容量无限大

4. 默认多个AZ互相备份

5. 命名需要全局唯一

    

### 考点

1. 文件上传会收到HTTP 200 Code
2. PUT new object 会即时生效 (read after write consistency)
3. Overwrite PUT或者delete会有很小的延迟（Eventual Consistency)
4. Versioning 版本控制（可以防止误删）
5. Server Access Logging 会对所有访问进行记录.

