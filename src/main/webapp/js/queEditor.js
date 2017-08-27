<!-- 实例化编辑器 -->
var ue = UE.getEditor('container',{
    toolbars: [
        ['undo', 'redo','bold', 'italic', 'underline', 'strikethrough',
            'superscript', 'subscript',
            'autotypeset', 'blockquote',  '|',
            'simpleupload', 'insertimage', 'imagenone',
            'imageleft', 'imageright', 'imagecenter','cleardoc'],
        ['indent','justifyleft', 'justifyright', 'justifycenter',
            'justifyjustify','touppercase', 'tolowercase','|','insertorderedlist',
            'insertunorderedlist','|',
            'horizontal','link','unlink'
        ]
    ],
    initialFrameWidth:600,
    initialFrameHeight:300,
    allHtmlEnabled:false,
    elementPathEnabled:false,
    wordCount:true, //是否开启字数统计
    maximumWords: 800,//允许的最大字符数
    autoHeightEnabled:false,
    pasteplain:true,
    fontfamily: {
        label: '',
        name: 'yahei',
        val: '微软雅黑,Microsoft YaHei'
    },
    fontsize:16,
    zIndex:1
});