<div class="n-head">
    <div class="g-doc f-cb">
        <div class="user">
        <#if user?has_content>
            <#if user.usertype=="1">卖家<#else>买家</#if>你好，<span class="name">${user.username}</span>！<a href="/spring-mall/mall/logout">[退出]</a>
        <#else>
            请<a href="/spring-mall/mall/goLogin">[登录]</a>
        </#if>
        </div>
        <ul class="nav">
            <li><a href="/spring-mall/mall/index?type=0">首页</a></li>
            <#if user?has_content && user.usertype=="0">
            <li><a href="/spring-mall/mall/account">账务</a></li>
            <li><a href="/spring-mall/mall/cart">购物车</a></li>
            </#if>
            <#if user?has_content && user.usertype=="1">
            <li><a href="/spring-mall/mall/public">发布</a></li>
            </#if>
        </ul>
    </div>
</div>