<!DOCTYPE html>
<html>
<#include "/head.ftl">
<body>
<#include "/support.ftl">
<#include "/header.ftl">
<#assign total = 0>
<div class="g-doc">
    <div class="m-tab m-tab-fw m-tab-simple f-cb">
        <h2>已购买的内容</h2>
    </div>
    <#if !buyList?has_content>
    <div class="n-result">
        <h3>暂无内容！</h3>
    </div>
    <#else>
    <table class="m-table m-table-row n-table g-b3">
        <colgroup><col class="img"/><col/><col class="time"/><col class="price"/></colgroup>
        <thead>
            <tr><th>内容图片</th><th>内容名称</th><th>购买时间</th><th>购买价格</th></tr>
        </thead>
        <tbody>
            <#list buyList as x>
            <#assign total = total + x.buyPrice>
            <tr>
                <td><a href="/spring-mall/mall/show?id=${x.id}"><img src="${x.image}" alt=""></a></td>
                <td><h4><a href="/spring-mall/mall/show?id=${x.id}">${x.title}</a></h4></td>
                <td><span class="v-time">${x.buyTime}</span></td>
                <td><span class="v-unit">¥</span><span class="value">${x.buyPrice}</span></td>
            </tr>
            </#list>
        </tbody>
        <tfoot>
            <tr>
                <td colspan="3"><div class="total">总计：</div></td>
                <td><span class="v-unit">¥</span><span class="value">${sum}</span></td>
            </tr>
        </tfoot>
    </table>
    </#if>
</div>
<#include "/footer.ftl">
</body>
</html>