<template>
    <div class="coupon-item">
        <img v-bind:src="coupon.imageUrl"
             v-bind:alt="coupon.imageUrl">
        <div class="coupon-list">
            <h3>Название: {{coupon.name}}</h3>
            <h3>Статус: {{coupon.status}}</h3>
            <h3 v-if="coupon.dateEnd!==''">Окончание акции: {{coupon.dateEnd}}</h3>
            <a class="button" :href="coupon.gotoLink">
                В магазин</a>
        </div>
    </div>
</template>

<script>
    export default {
        props: {
            coupon: {
                type: Object,
                required: true,
            }
        }
    }

</script>

<style scoped>
    * {
        box-sizing: border-box;
    }

    .coupon-item {
        width: 400px;
        text-align: center;
        margin: .3rem .3rem auto;
        border: 2px solid #F5F5F5;
        border-radius: 1rem;
        background: white;
        transition: 1s ease;
    }

    .coupon-item:hover {
        border: 2px solid rgb(0, 131, 255);
    }

    .coupon-item img {
        display: block;
        width: 100%;
        height: 100%;
        border-radius: 1rem 1rem 0 0;
    }

    .coupon-list {
        background: aliceblue;
        padding: 15px 0;
        border-radius: 0 0 1rem 1rem;
    }

    .coupon-list h3 {
        font-size: 18px;
        font-weight: 400;
        color: #444444;
        margin: 0 0 10px 0;
    }

    .button {
        text-decoration: none;
        display: inline-block;
        padding: 0 12px;
        background: #cccccc;
        color: #ffffff;
        font-size: 12px;
        line-height: 28px;
        transition: .3s ease-in;
        text-transform: uppercase;

    }

    .coupon-item:hover .button {
        background: rgb(0, 131, 255);
    }

</style>