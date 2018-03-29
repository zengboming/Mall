<!DOCTYPE html>
<html>
<#include "/head.ftl">
<body>
<#include "/support.ftl">
<#include "/header.ftl">
<div class="g-doc" id="settleAccount">
    <div class="m-tab m-tab-fw m-tab-simple f-cb" >
        <h2>已添加到购物车的内容</h2>
    </div>
    <#if !products?has_content>
    <div class="n-result">
        <h3>暂无内容！</h3>
    </div>
    <#else>
 	<table id="newTable" class="m-table m-table-row n-table g-b3">
    </table>
    </#if>
 	<div id="act-btn">
 		<button class="u-btn u-btn-primary" id="back">退出</button>
 		<button class="u-btn u-btn-primary" id="Account">购买</button>
 	</div>
</div>
<#include "/footer.ftl">
<script type="text/javascript" src="../js/global.js"></script>
<script type="text/javascript" src="../js/settleAccount.js"></script>
</body>
</html>