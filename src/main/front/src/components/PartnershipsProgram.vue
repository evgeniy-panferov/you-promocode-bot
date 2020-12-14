<template>
    <div class="coupons">
        <trinity-rings-spinner v-if="loading"
                               style="margin: 0 auto;" :animation-duration="1000" :size="500" color="rgb(0, 131, 255)"/>
        <Program
                v-for="program in programs" :key="program.id"
                v-bind:program="program"
        />
    </div>
</template>
<script>
    import Program from "./Program";
    import {TrinityRingsSpinner} from 'epic-spinners'

    export default {
        components: {
            Program,
            TrinityRingsSpinner
        },
        data() {
            return {
                programs: [],
                loading: true
            }
        },

        computed: {
            resource: function () {
                return this.$resource('http://localhost:8080/api/partnerships-programs')
            }
        },

        created() {
            this.resource.get()
                .then(response => {
                    this.programs = response.data
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

</style>