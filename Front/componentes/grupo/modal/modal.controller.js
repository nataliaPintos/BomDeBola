(function () {
    'use strict';

    angular
        .module('app')
        .controller('ModalController', modalController);

    function modalController(close) {
        var vm = this;
        vm.isModal = true;
        vm.closeModal = closeModal;
       

        function closeModal() {
            vm.isModal = false;
            close();
        }
    }

}());