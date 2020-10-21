new Vue({
    el: '#app',
    vuetify: init_vuetify, // using 'init_vuetify' variable from common.js
    data: {
        drawer: null
    },
    components: {
        'app-layout' : httpVueLoader('/farmersWelfareProgram/component/app-layout.vue')
    }
});