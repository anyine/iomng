Ext.define('VMS.store.InnerCarStore', {
    extend: 'Ext.data.Store',
    model:'VMS.model.InnerCar',
 //   autoLoad:true,
    proxy:{
    	type:'ajax',
    	api:{
    		create:'innerCar/add',
    		read:'innerCar',
    		update:'innerCar/update',
    		destroy:'innerCar/delete'
    	},
    	reader:'json',
    	writer:{
    		type:'json',
    		writeAllFields :false
    	}
    }
});
