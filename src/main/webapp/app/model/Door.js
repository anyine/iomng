Ext.define('VMS.model.Door',{
	extend:'Ext.data.Model',
	fields:[
	        {name:'id',type:'int'},
	        {name:'organization.id',type:'int'},
	        {name:'organization.name',type:'varchar'},
	        {name:'name',type:'varchar'}
	        ],
	        idProperty:'id'
});