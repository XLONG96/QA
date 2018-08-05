$(function(){

    //que.getQue();
    //que.appendQue();

    /*var len = $("div[name=content] p").height();
    //alert(len);
    if(len<90){
        $(".que-all").hide();
        $(".que-all-close").hide();
    }*/
    $(document).on("click",".que-all",function(){
        $(this).parent().find("div[name=content]").removeClass("content");
        $(this).parent().find(".que-all").hide();
        $(this).parent().find(".que-all-close").show();
    });

    $(document).on("click",".que-all-close",function(){
        $(this).parent().find("div[name=content]").addClass("content");
        $(this).parent().find(".que-all").show();
        $(this).parent().find(".que-all-close").hide();
    });

});

var que = {
    getQue: function(){
        $.ajax({
            url: "/home/asy-que",
            success: function (data) {

            }
        });
    },

    appendQue: function(){
        var detail =
        "<div class=\"box-que\">\n" +
        "                <div class=\"que-detail\">\n" +
        "                    <p>\n" +
        "                        <img src=\"images/temp.jpg\" class=\"que-img\" />\n" +
        "                        <strong class=\"name\">Marry欣</strong>\n" +
        "                    </p>\n" +
        "                    <p><strong class=\"que\"><a href=\"#\">说说你的故事</a></strong></p>\n" +
        "                    <p name=\"content\" class=\"content\">\n" +
        "                    这仅仅是一个测试\n" +
        "                    </p>\n" +
        "                    <p class=\"que-all\"><span class=\"glyphicon glyphicon-chevron-down\"></span> 阅读全文</p>\n" +
        "                    <p class=\"que-all-close\" style=\"display:none\"><span class=\"glyphicon glyphicon-chevron-up\"></span> 收起</p>\n" +
        "                    <div class=\"que-bottom\">\n" +
        "                        <p><span class=\"glyphicon glyphicon-thumbs-up\"></span> 1024赞</p>\n" +
        "                        <p><span class=\"glyphicon glyphicon-comment\"></span> 1024评论</p>\n" +
        "                        <p><span class=\"glyphicon glyphicon-star\"></span> 1024收藏</p>\n" +
        "                        <p><span class=\"glyphicon glyphicon-bell\"></span> 举报</p>\n" +
        "                        <p><span class=\"glyphicon glyphicon-calendar\"></span> 2017-08-02</p>\n" +
        "                    </div>\n" +
        "                </div>\n" +
        "            </div>";

         $(".box").append(detail);
    }


}