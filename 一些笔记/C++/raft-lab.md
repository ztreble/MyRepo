**一开始就气势汹汹的raft-lab（笑）**

**开始之前首先要阅读助教的博客https://thesquareplanet.com/blog/students-guide-to-raft/**

**实验的第一部分建立基于一致性的（consecsus-based）log library，第二部分再日志库中添加key-value store，第三部分在多个容错集群之间分片关键空间**

不需要实现section 6
https://thesquareplanet.com/blog/students-guide-to-raft/2D部分发生了更改



- 服务器字段
稳定状态
currentTerm 服务器看到的最后选举日期
votedFor 当前日期内的选举人id
log[]

不稳定状态-所有服务器
commitIndex 已知要提交的最高日志条目的索引
lastApplied 应用到状态机的最高级别日志条目索引

不稳定状态-leaders
nextIndex[] 对每个服务器发送的下一个日志条目
matchIndex[] 对每个服务器复制的最高日志条目索引


- RequestVote RPC 收集选票
参数
term 任期
candidateId 候选人ID
lastLogIndex 
lastLogTerm 
返回值
term
voteGranted bool


return false if term < currentTerm


- AppendEntries RPC 由leader调用以复制日志条目 也用作心跳
term
leaderId
prevLogIndex
prevLogTermentries
leaderCommit 

返回值
term currentTerm
success


如果term < currentTerm，则返回false
如果prevLogIndex中没有与prevLogTerm匹配的条目，则返回false 
如果现有条目与新条目冲突(相同索引但不同的条目)，删除现有条目及其之后的所有条目
追加日志中没有的任何新条目
如果leaderCommit > commitIndex，设置commitIndex = min(leaderCommit，最后一个新条目的索引)

- 规则
所有servers：
如果commitIndex>lastApplied 应用log[lastApplied]
term T>currentTerm 设置currentTerm=T 转换为follower

follows：
响应候选人和leader的rpc
选举超时：没有接受到AppendEntries/投票：转换为candidate

candidates：
转换为选举人时开始选举
大多数投票：变成leader
appednEntries rpc ： 变成follower


leaders:
开始时发送
AppendEntries rpc心跳包
附加从client收到的日志，应用到状态机后响应
如果log index>=nextIndex 发送appendEntries rpc （nextIndex）

