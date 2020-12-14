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
    import Program from "../components/Program";
    import {TrinityRingsSpinner} from 'epic-spinners'

    export default {
        components: {
            Coupon,
            Program,
            TrinityRingsSpinner
        },
        data() {
            return {
                coupons: [],
                loading: true,
            }
        },

        computed: {
            resource: function () {
                return this.$resource('http://localhost:8080/api/coupons{/id}')
            }
        },

        created() {

            var option = {
                id: this.$route.params.id,
                isDatabase: this.$route.query.isDatabase
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