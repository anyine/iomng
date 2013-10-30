Ext.define('VMS.model.InnerCar',{
	extend:'Ext.data.Model',
	fields:[
	        {name:'id',type:'long'},
	        {name:'license',type:'varchar'},
	        {name:'card.number',type:'varchar'},
	        {name:'user.id',type:'varchar'},
	        {name:'user.realname',type:'varchar'},
	        {name:'organization.id',type:'varchar'},
	        {name:'organization.name',type:'varchar'},
	        {name:'type.id',type:'varchar'},
	        {name:'type.name',type:'varchar'},
	        {name:'level',type:'varchar'},
	        {name:'status',type:'varchar'}
	        ],
	        idProperty:'id',
	        
});