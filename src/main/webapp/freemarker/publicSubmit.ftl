<!DOCTYPE html>
<html>
<#include "/head.ftl">
<body>
<#include "/support.ftl">
<#include "/header.ftl">
<div class="g-doc">
    <#if product?has_content>
    <div class="n-result">
        <h3>发布成功！</h3>
        <p><a href="/spring-mall/mall/show?id=${product.id}">[查看内容]</a><a href="/spring-mall/mall/index?type=0">[返回首页]</a></p>
    </div>
    <#else>
    <div class="n-result">
        <h3>发布失败！</h3>
        <p><a href="/spring-mall/mall/public">[重新发布]</a><a href="/spring-mall/mall/index?type=0">[返回首页]</a></p>
    </div>
    </#if>
</div>
<#include "/footer.ftl">
</body>
</html>