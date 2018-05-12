<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html class="root61">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta name="format-detection" content="telephone=no">

<link rel="stylesheet" type="text/css" href="/css/taotao.css"
	media="all" />
<link rel="stylesheet" type="text/css" href="/css/pshow.css" media="all" />
<title>商品已成功加入购物车</title>

</head>
<body>
	<jsp:include page="commons/header.jsp" />
	<div class="main">
		<div class="success-wrap">
			<div class="w" id="result">
				<div class="m succeed-box">
					<div class="mc success-cont">
						<div class="success-lcol">
							<div class="success-top">
								<b class="succ-icon"></b>
								<h3 class="ftx-02">商品已成功加入购物车！</h3>
							</div>
							<div class="p-item">
								<div class="p-img">
									<a href="https://item.jd.com/4749506.html" target="_blank"><img
										src="./商品已成功加入购物车_files/592e9b89N008ee685.jpg"
										clstag="pageclick|keycount|201601152|11"></a>
								</div>
								<div class="p-info">
									<div class="p-name">
										<a href="https://item.jd.com/4749506.html" target="_blank"
											clstag="pageclick|keycount|201601152|2"
											title="TCL 55A950C 55英寸32核人工智能 HDR曲面超薄4K电视金属机身（枪色）">TCL
											55A950C 55英寸32核人工智能 HDR曲面超薄4K电视金属机身（枪色）</a>
									</div>
									<div class="p-extra">
										<span class="txt" title="55吋 超薄曲面 人工智能">尺码：55吋 超薄曲面
											人工智能</span><span class="txt">/ 数量：1</span>
									</div>
								</div>
								<div class="clr"></div>
							</div>
						</div>
						<div class="success-btns success-btns-new">
							<div class="success-ad">
								<a
									href="https://cart.jd.com/addToCart.html?rcd=1&amp;pid=4749506&amp;pc=1&amp;eb=1&amp;rid=1516959533504&amp;em=#none"></a>
							</div>
							<div class="clr"></div>
							<div>
								<a class="btn-tobback" href="javascript:history.back();"
									target="_blank" clstag="pageclick|keycount|201601152|3">查看商品详情</a><br/>
								<a	class="btn-addtocart"
									href="/cart/cart.html"
									id="GotoShoppingCart" clstag="pageclick|keycount|201601152|4"><b></b>去购物车结算</a>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

	</div>
	<!--service start-->
	<!--footer end-->
	<script type="text/javascript"
		src="./商品已成功加入购物车_files/saved_resource(5)"></script>
	<script type="text/javascript" src="./商品已成功加入购物车_files/config.js.下载"></script>
	<script type="text/javascript"
		src="./商品已成功加入购物车_files/initCartNew.js.下载"></script>
	<script type="text/javascript">
		seajs.use([ "jdf/1.0.0/unit/globalInit/5.0.0/globalInit.js",
				'jdf/1.0.0/ui/switchable/1.0.0/switchable' ], function(
				globalInit) {
			globalInit();
			$('#similar, #promotion').switchable({
				type : 'focus',
				navItem : 's-item',
				navSelectedClass : 'curr',
				contentClass : 's-panel-main',//主体 
				mainClass : 's-panel',
				prevClass : 's-prev',
				nextClass : 's-next',
				hasPage : true,
				autoPlay : false
			});
			$('.goods-list .item').hover(function() {
				$(this).addClass('hover');
			}, function() {
				$(this).removeClass('hover');
			})
			$('#similar, #promotion').hover(function() {
				$('.s-page', $(this)).show();
			}, function() {
				$('.s-page', $(this)).hide();
			})
		});
	</script>
	<script type="text/javascript">
		(function() {
			var ja = document.createElement('script');
			ja.type = 'text/javascript';
			ja.async = true;
			ja.src = '//wl.jd.com/wl.js';
			var s = document.getElementsByTagName('script')[0];
			s.parentNode.insertBefore(ja, s);
		})();
	</script>

	<script src="./商品已成功加入购物车_files/td.js.下载"></script>
	<script src="./商品已成功加入购物车_files/y.html"></script>
	<jsp:include page="commons/footer.jsp" />
</body>
</html>