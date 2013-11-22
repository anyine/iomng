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
	    		'select': this.changePage
	    	},
	    	'viewport grid':{
	    		'show':this.onGridShow
	    	}
    	});
    },
    onGridShow:function(me,eo){
		me.getStore().load();
	}   ,
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