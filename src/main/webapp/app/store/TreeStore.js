Ext.define('VMS.store.TreeStore', {
    extend: 'Ext.data.TreeStore',
    autoLoad:true,
    proxy:{
    	type:'ajax',
    	url:'data/menu.jsp',
    	reader:'json'
    }
});
