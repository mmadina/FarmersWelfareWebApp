<template>
    <v-app id="inspire">
        <v-navigation-drawer
                v-model="drawer"
                :mini-variant="miniVariant"
                :expand-on-hover="expandOnHover"
                app
                color="secondary"
        >
            <v-list
                    dense
                    nav
                    class="py-0"
            >
                <v-list-item two-line :class="miniVariant && 'px-0'">
                    <v-list-item-avatar>
                        <v-icon>mdi-account-circle</v-icon>
                    </v-list-item-avatar>

                    <v-list-item-content>
                        <v-list-item-title>Welcome</v-list-item-title>
                        <v-list-item-subtitle>
                            {{ username }}
                        </v-list-item-subtitle>
                    </v-list-item-content>
                </v-list-item>

                <v-divider></v-divider>

                <v-list-item
                        v-for="item in items"
                        :key="item.title"
                        link
                        :href= "item.link"
                        :target="item.target"
                >
                    <v-list-item-icon>
                        <v-icon>{{ item.icon }}</v-icon>
                    </v-list-item-icon>

                    <v-list-item-content>
                        <v-list-item-title>{{ item.title }}</v-list-item-title>
                    </v-list-item-content>
                </v-list-item>
            </v-list>
        </v-navigation-drawer>

        <v-app-bar
                app
                color="primary"
                dark
        >
            <v-app-bar-nav-icon @click.stop="drawer = !drawer"></v-app-bar-nav-icon>
            <v-toolbar-title>Farmers Welfare Program</v-toolbar-title>
            <v-spacer></v-spacer>
            <v-icon>{{ icon }}</v-icon>
        </v-app-bar>

        <v-content>
            <v-container
                    fluid
            >
               <slot></slot>
            </v-container>
        </v-content>

        <v-footer
                color="primary"
                app
        >
           <span class="white--text">
               Copyright &copy; University of Central Missouri
           </span>
            <v-spacer></v-spacer>
            <span class="white--text">
            v1.1
            </span>
        </v-footer>
    </v-app>
</template>
<script>
    module.exports = {
        props: ['username', 'icon'],
        data: function () {
            return {
                vmodel: true,
                drawer: null,
                color: 'primary',
                loading: false,
                miniVariant: true,
                expandOnHover: true,
                items: [
                    {title: 'Home', icon: 'mdi-home', link: '/farmersWelfareProgram/home', target: '_self' },
                    {title: 'Create Application', icon: 'mdi-view-dashboard', link: '/farmersWelfareProgram/create', target: '_self' },
                    {title: 'Search Application', icon: 'mdi-database-search', link: '/farmersWelfareProgram/search', target: '_self' },
                    {title: 'Logout', icon: 'mdi-logout', link: '/farmersWelfareProgram/logout', target: '_self' },
                ]
            }
        }
    }
</script>