Ext.define('VMS.view.Viewport', {
	extend: 'Ext.container.Viewport',
	layout: 'border',
    defaults:{
        border: false
    },
    items:[{
        region: 'north',
        xtype: 'pageHeader'
    },{
    	id:'treemenu',
    	region: 'west',
    	xtype: 'treePanel',
        margin: '2 0 0 3',
    	width: 250,
    	maxWidth:300,
    	minWidth:100,
    	split:true
    },{
    	id:'pageContent',
    	region: 'center',
        margin: '2 0 0 3',
        flex: 1,
        xtype  : 'pageContent'
    },{
    	region: 'south',
        xtype: 'pageFooter'
    }]
});