Ext.Loader.setConfig({
	enabled : true,
	paths : {
		'VMS' : 'app',
		'Ext.ux' : 'ux'
	}
});
Ext.require('VMS.store.OrganizationStore');
//添加自定义验证
Ext.apply(Ext.form.field.VTypes, {
	daterange : function(val, field) {
		var date = field.parseDate(val);
		if (!date) {
			return false;
		}
		if (field.startDateField
				&& (!this.dateRangeMax ||! (date.getTime() > this.dateRangeMax
						.getTime()))) {
			var start = field.up('form').down('#' + field.startDateField);
			start.setMaxValue(date);
			//start.validate();
			this.dateRangeMax = date;
		} else if (field.endDateField
				&& (!this.dateRangeMin || !(date.getTime() < this.dateRangeMin
						.getTime()))) {
			var end = field.up('form').down('#' + field.endDateField);
			end.setMinValue(date);
			//end.validate();
			this.dateRangeMin = date;
		}
		return true;
	},
	daterangeText : 'Start date must be less than end date'
});

Ext.onReady(function() {
	Ext.application({
		name : 'VMS',
		appFolder : 'app',
		autoCreateViewport : true,
		controllers : [ 'Main', 'InnerCarCtrl', 'DoorCtrl', 'CarTypeCtrl',
				'UserCtrl', 'InnerCarRecordCtrl', 'InnerPersonRecordCtrl',
				'OrganizationCtrl', 'InnerPersonCtrl', 'DispatchCarFormCtrl',
				'LeaveCtrl','NotifyCtrl' ]
	});
});
