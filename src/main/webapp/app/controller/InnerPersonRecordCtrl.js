Ext.define('VMS.controller.InnerPersonRecordCtrl', {
			extend : 'Ext.app.Controller',
			views : ['innerPersonRecord.List'],
			stores : ['InnerPersonRecordStore'],
			models : ['InnerPersonRecord'],

			init : function() {
				console.log('innerperson record controller init');
				this.control({
							'innerpersonrecordlist form datetimefield' : {
								change : this.onTimeFieldChange
							},
							'innerpersonrecordlist toolbar button[action=reset]' : {
								click : this.onRestClick
							}
						});
			},
			onTimeFieldChange : function(me, newValue, oldValue, eOpts) {
				var store = this.getInnerPersonRecordStoreStore();
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
			onRestClick : function(me) {
				me.up('grid').down('form').getForm().reset();
			}
		});