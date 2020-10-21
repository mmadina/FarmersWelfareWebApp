new Vue({
    el: '#app',
    vuetify: init_vuetify, // using 'init_vuetify' variable from common.js
    data () {
        return {
            isPasswordVisible: String,
            displayErrorMsg : true,
            loading: false,
        }
    },
    created(){
        loading: false
    },
    methods: {
        submit() {
            this.loading = true;
            document.getElementById('loginForm').submit();
        },
        inputTextChanged() {
            this.displayErrorMsg = false;
        }
    }
});