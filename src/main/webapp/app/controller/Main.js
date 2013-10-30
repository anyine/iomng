Ext.define('VMS.controller.Main', {
    extend: 'Ext.app.Controller',
    views:['Viewport','Header','Footer','Tree','Content'],
    stores:['TreeStore'],
    models:['InnerCar'],
    refs:[{
    		ref:'pageContent',
    		selector:'viewport > #pageContent'
    	}],
    
    init:function(){
    	this.control({
	    	'#treemenu':{
	    		'select': this.changePage,
                'itemcontextmenu': this.showContextMenu
	    	},
            'menu [action=edit]':{
                'click': this.editNode
            },
            'menu [action=add]':{
                'click': this.addNode
            },
            'menu [action=del]':{
                'click': this.delNode
            },
            'Edit [action=submit]':{
                'click': this.saveNodeText
            }
    	});
    },

    //提交表单 更新节点信息
    saveNodeText:function(me,e){
        var form  = me.up().up(),
            //node是点击右键的时候传递过来的待修改节点信息
            node  = form.up().node,
            //获取新的节点名称
            value = form.getForm().findField('node').getValue();

        //设置节点名称为修改后名称
        node.set({text:value});
        //修改完成之后隐藏弹出框
        form.up().hide();
    },

    //编辑节点信息
    editNode:function(me,e){
        var node = me.up().node,
            //继续在页面之间传递 操作节点的信息
            edit = Ext.create('VMS.view.Edit',{
                node: node
            });
        //用原节点的名称填充表单信息
        edit.down('form').getForm().findField('node').setValue(node.get('text'));
        edit.show();
    },

    addNode:function(me,e){
        Ext.Msg.alert('提示','您点击的是添加按钮...');
    },

    delNode:function(me,e){
        Ext.Msg.alert('提示','您点击的是删除按钮...');
    },

    //点击右键 显示右键菜单
    showContextMenu:function(me,rec,item,index,e){
        //将tree节点的数据传给menu，实现页面之间参数的传递
        var menu = Ext.create('VMS.view.Menu',{
            node:rec
        });

        //阻止浏览器默认右键事件
        e.preventDefault();
        e.stopEvent();

        menu.showAt(e.getXY());
    },

    //点击树形节点  切换页面
    changePage:function(me,rec,index,e){
    //如果点击的节点为非叶子节点，则不作任何操作
    	if(!rec.isLeaf()){
    		return;
    	}
    	
    	var id=rec.get('id');
    	console.log('menu--'+id);
    	var title=rec.get('text');
    	var pageContent=this.getPageContent();
    	var tab=pageContent.getComponent(id);
    	if(!tab){
    		pageContent.add({
	    		id:id,
	    		xtype:id,
	    		title:title,
	    		closable:true,
	    		closeAction:'destory',
	    		iconCls:id
    		}).show();
    	}else{
    		pageContent.setActiveTab(tab);
    	}
    	
    }
});