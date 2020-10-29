
new Vue({
    el: '#app',
    vuetify: init_vuetify,
    data: () => ({
        dialog: true,
        valid: false,
        firstName: "",
        lastName: "",
        email: "",
        password: "",
        verify: "",
        phone:"",
        addressLine1:"",
        addressLine2:"",
        city:"",
        state:"",
        zip:"",
        displaySuccessMsg:false,
        displayErrorMsg:false,
        loading:false,
        loginEmailRules: [
            v => !!v || "Required",
            v => /.+@.+\..+/.test(v) || "E-mail must be valid"
        ],
        emailRules: [
            v => !!v || "Required",
            v => /.+@.+\..+/.test(v) || "E-mail must be valid"
        ],
        show1: false,
        rules: {
            required: value => !!value || "Required.",
            email: v => !v || /^\w+([.-]?\w+)*@\w+([.-]?\w+)*(\.\w{2,3})+$/.test(v) || 'E-mail must be valid',
            min: v => (v && v.length >= 4) || "Min 4 characters"
        }
    }),
    computed: {
        passwordMatch() {
            return () => this.password === this.verify || "Password must match";
        }
    },
    methods: {
        submit() {
            this.loading = true;
            const body = {
                'firstName': this.firstName,
                'lastName': this.lastName,
                'emailAddress': this.email,
                'password': this.password,
                'phoneNumber': this.phone,
                'addressLine1': this.addressLine1,
                'addressLine2': this.addressLine2,
                'city': this.city,
                'state': this.state,
                'zipCode': this.zip
            }
            fetch('/farmersWelfareProgram/register/user', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json;charset=utf-8'
                },
                body: JSON.stringify(body)
            }).then(async response => {
                const data = await response.text();
                this.loading = false;
                this.displayErrorMsg = false;
                this.displaySuccessMsg = true;
                window.scrollTo(0,0);
                if (!response.ok) {
                    return Promise.reject(data);
                }
            }).catch(e => {
                this.loading = false;
                this.displayErrorMsg = true;
                this.displaySuccessMsg = false;
                window.scrollTo(0,0);
            })
        },
        inputTextChanged() {
            this.displayErrorMsg = false;
        },
        reset() {
            this.$refs.form.reset();
        },
        resetValidation() {
            this.$refs.form.resetValidation();
        }
    }
});