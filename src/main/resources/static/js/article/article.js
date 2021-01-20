/**
 * 热搜词表
 */
layui.use('core', function () {
    var table = layui.table;
    var util = layui.util;
    var laydate = layui.laydate;
    var form = layui.form;
    var core = layui.core;
    var $ = layui.jquery;

    initTable({});

    /**
     * 查询数据并组装到layui表格
     * @param where
     */
    function initTable(where) {
        //第一个实例
        table.render({
                elem: '#table'
                , url: '/platform/article/initTable' //数据接口
                , where: where
                , toolbar: '#titleToolbar' //开启头部工具栏，并为其绑定左侧模板
                , defaultToolbar: ['filter']
                , title: '机构数据表'
                , cols: [[ //表头
                    {field: 'id', title: 'ID', width: 80, fixed: 'left', unresize: true, sort: true}
                    , {field: 'title', title: '标题'}
                    , {field: 'countcollection', title: '总收藏量'}
                    , {field: 'countthumbs', title: '总点赞量'}
                    , {field: 'countbrowse', title: '总浏览量'}
                    , {field: 'countcomment', title: '总评论量'}
                    // , {field: 'headpicture', title: '头图', width: 100,
                    //     templet: function (d) {
                    //         return '<div><img src=' + d.headpicture + '></div>'
                    //     }
                    // }
                    // , {field: 'picture', title: '图片', width: 100,
                    //     templet: function (d) {
                    //         return '<div><img src=' + d.picture + '></div>'
                    //     }
                    // }
                    , {
                        field: 'releasetime', title: '发布时间', sort: true,
                        templet: function (d) {
                            return d.releasetime;
                            // return util.toDateString(d.releasetime, "yyyy-MM-dd HH:mm:ss");
                        }
                    }
                    ,{field: 'sort', title: '排序',sort: true}
                    ,{field: 'isrecommend', title: '推荐',sort: true,
                        templet: function(d){
                            if(d.isrecommend == true){
                                return '<b style="color: red;">推荐</b>';
                            }else {
                                return '';
                            }
                        }
                    }
                    , {fixed: 'right', title: '操作', toolbar: '#rowToolBar', width: 170, unresize: true}
                ]]
                ,
                page: true //开启分页
            }
        );
    }

//头工具栏事件
    table.on('toolbar(tableFilter)', function (obj) {
        switch (obj.event) {
            case 'add':
                core.openIframeDialog('添加咨询', '/platform/article/input?type=add', ['100%', '90%'], false, initTable);
                break;
            //自定义头工具栏右侧图标 - 提示
            case 'LAYTABLE_TIPS':
                layer.alert('这是工具栏右侧自定义的一个图标按钮');
                break;
        }
        ;
    });

//监听行工具事件
    table.on('tool(tableFilter)', function (obj) {
        var data = obj.data;
        if (obj.event === 'del') {
            layer.confirm('真的删除资讯么？', function (index) {
                layer.close(index);
                //向服务端发送删除指令
                var resultData = core.ajax('/platform/article/delete', false, 'POST', 'id=' + data.id);
                if (resultData != false) {
                    layer.msg('操作成功！', core.showtime);
                    initTable({});
                }
            });
        } else if (obj.event === 'edit') {
            core.openIframeDialog('修改热资讯', '/platform/article/input?type=update&id=' + data.id, ['100%', '90%'], false, initTable);
        } else if(obj.event === 'isrecommend'){
            core.openIframeDialog('推荐','/platform/article/isrecommend?id='+data.id,['50%', '40%'],false,initTable);
        }
    });

//开始时间
    laydate.render({
        elem: '#startTime' //指定元素
    });
//结束时间
    laydate.render({
        elem: '#endTime' //指定元素
    });

//按条件检索机构数据
    form.on('submit(searchFrom)', function (data) {
        initTable(data.field);
        layer.msg('操作成功', core.showtime)
        return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
    })
})
;

