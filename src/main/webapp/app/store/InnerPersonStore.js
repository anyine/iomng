Ext.define('VMS.store.InnerPersonStore', {
    extend: 'Ext.data.Store',
    model:'VMS.model.InnerPerson',
 //   autoLoad:true,
    proxy:{
    	type:'ajax',
    	api:{
    		read:'innerPerson',
    		destroy:'innerPerson/delete'
    	},
    	reader:'json',
    	writer:{
    		type:'json',
    		writeAllFields :false
    	}
    }
});
