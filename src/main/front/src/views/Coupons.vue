<template>
    <div class="coupons">
        <trinity-rings-spinner v-if="loading"
                               style="margin: 0 auto;" :animation-duration="1000" :size="500" color="rgb(0, 131, 255)"/>
        <coupon
                v-else-if="coupons.length"
                v-for="coupon in coupons" :key="coupon.id"
                v-bind:coupon="coupon"
        />

        <div class="not-found" v-else-if="coupons.length===0">Купоны не найдены!
            <img v-bind:src="'http://risovach.ru/upload/2017/11/mem/a-kto-eto-sdelal_162127843_orig_.jpg'">
        </div>
    </div>
</template>
<script>
    import Coupon from "../components/Coupon";
    import {TrinityRingsSpinner} from 'epic-spinners'

    export default {
        components: {
            Coupon,
            TrinityRingsSpinner
        },
        data() {
            return {
                coupons: [],
                loading: true,
                isDatabase: ''
            }
        },

        computed: {
            resource: function () {
                return this.$resource('http://localhost:8080/coupons{/id}')
            }
        },

        created() {
            this.isDatabase = this.$route.params.isDatabase;
            var option = {
                id: this.$route.params.id,
                isDatabase: this.isDatabase
            }

            this.resource.get(option)
                .then(response => {
                    this.coupons = response.data
                    this.loading = false
                })
                .catch(error => {
                    console.log(error)
                })
        }
    }

</script>

<style scoped>
    .coupons {
        display: flex;
        flex-wrap: wrap;
        justify-content: center;
        align-items: center;
        align-content: center;
    }

    .not-found {
        font-size: 30px;
    }

    .not-found img {
        min-width: 100%;
        min-height: 100%;
    }

</style>