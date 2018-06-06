/**
 * 编辑-角色管理js
 */
var vm = new Vue({
    el: '#app',
    data: {
        role: {
            orgId: 0,
            orgName: null
        }
    },
    methods: {
        setForm: function () {
            $.SetForm({
                url: '/sys/role/info/' + vm.role.roleId + '?_' + $.now(),
                success: function (data) {
                    vm.role = data;
                }
            });
        },
        acceptClick: function () {
            if (!$('#form').Validform()) {
                return false;
            }
            $.ConfirmForm({
                url: '/sys/role/update?_' + $.now(),
                param: vm.role,
                success: function (data) {
                    $.currentIframe().vm.load();
                }
            });
        }
    }
})