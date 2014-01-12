Ext.define('VMS.controller.InnerCarRecordCtrl', {
			extend : 'Ext.app.Controller',
			views : ['innerCarRecord.List'],
			stores : ['InnerCarRecordStore'],
			models : ['InnerCarRecord'],

			init : function() {
				// console.log('innercar record controller init');
				this.control({
							'innercarrecordlist form datetimefield' : {
								change : this.onTimeFieldChange
							},
							'innercarrecordlist toolbar button[action=reset]':{
								click:this.onRestClick
							}
						});
			},
			onTimeFieldChange : function(me, newValue, oldValue, eOpts) {
				var store = this.getInnerCarRecordStoreStore();
				store.clearFilter(true);
				store.filter({
							filterFn : function(item) {
								if ('startdt' == me.getName()) {
									return item.get("date") >= newValue;
								} else if ('enddt' == me.getName()) {
									return item.get("date") <= newValue;
								}
							}
						});
			},
			onRestClick:function(me){
				me.up('grid').down('form').getForm().reset();
			}
		});