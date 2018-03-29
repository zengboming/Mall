<!DOCTYPE html>
<html>
<#include "/head.ftl">
<body>
<#include "/support.ftl">
<#include "/header.ftl">
<div class="g-doc">
    <#if !product?has_content>
    <div class="n-result">
        <h3>内容不存在！</h3>
    </div>
    <#else>
    <div class="n-show f-cb" id="showContent">
        <div class="img"><img src="${product.image}" alt=""></div>
        <div class="cnt">
            <h2>${product.title}</h2>
            <p class="summary">${product.abstract}</p>
            <div class="price">
                <span class="v-unit">¥</span><span class="v-value">${product.price}</span>
            </div>
            <div class="num">购买数量：<span id="plusNum" class="lessNum"><a>-</a></span><span class="totalNum" id="allNum">${product.number}</span><span id="addNum" class="moreNum"><a>+</a></span></div>
            <div class="oprt f-cb">
                <#if user?has_content && user.usertype=="0">
                    <#if product.buy == "1">
                    <span class="u-btn u-btn-primary z-dis">已购买</span>
                    <span class="buyprice">当时购买价格：¥${product.buyPrice}</span>
                    <#else>
                    <button class="u-btn u-btn-primary" id="add" data-id="${product.id}" data-title="${product.title}" data-price="${product.price}">添加购物车</button>
                    </#if>
                </#if>
                <#if user?has_content && user.usertype=="1">
                <a href="/spring-mall/mall/edit?id=${product.id}" class="u-btn u-btn-primary">编 辑</a>
                </#if>
            </div>
        </div>
    </div>
    <div class="m-tab m-tab-fw m-tab-simple f-cb">
        <h2>详细信息</h2>
    </div>
    <div class="n-detail">
        ${product.content}
    </div>
    </#if>
</div>
<#include "/footer.ftl">
<script type="text/javascript" src="../js/global.js"></script>
<script type="text/javascript" src="../js/pageShow.js"></script>
</body>
</html>