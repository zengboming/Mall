<html lang="en-US">
    <head>
        <meta charset="UTF-8">
        <title>用户信息</title>
    </head>
    <body>
    	<p>Name：${user.name}</p>
    	<p>Password：${user.type}</p>
    	
    	<div class="n-head">
    <div class="g-doc f-cb">
        <div class="use">
            <#if user.type==1>卖家<#else>买家</#if>你好，<span class="name">${user.name}</span>！<a href="/logout">[退出]</a>
        </div>
        <ul class="nav">
            <li><a href="/">首页</a></li>
            <#if user && user.usertype==0>
            <li><a href="/account">账务</a></li>
            </#if>
            <#if user && user.usertype==1>
            <li><a href="/public">发布</a></li>
            </#if>
        </ul>
    </div>
</div>
    </body>
</html>