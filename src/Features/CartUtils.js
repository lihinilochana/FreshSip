export const totalItem = (cart) => {
    return cart.reduce((total, item) => total + item.quantity, 0);
};

export const totalPrice = (cart) => {
    return cart.reduce((total, item) => total + item.item_prize * item.quantity, 0).toFixed(2);
};