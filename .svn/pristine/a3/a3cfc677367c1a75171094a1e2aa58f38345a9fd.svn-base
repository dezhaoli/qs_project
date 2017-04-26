$(function(){
	pushHistory();
	window.addEventListener("popstate", function(e) {
		  webside.index.initHomePage();//跳转首页
}, true);
	function pushHistory() {
	    var state = {
	        title: "title",
	        url: "#"
	    };
	    window.history.pushState(state, "title", "#");
	}
	
});