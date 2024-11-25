const CartReducer = (state, action) => {
    switch (action.type) {
        case "Add":
            return [...state, action.product];

        case "Remove":
            return state.filter(p => p.item_id !== action.id);

        case "Increase":
            const IndexI = state.findIndex(p => p.item_id === action.id);
            state[IndexI].quantity += 1;
            return [...state];

        case "Decrease":
            const IndexD = state.findIndex(p => p.item_id === action.id);
            state[IndexD].quantity -= 1;
            return [...state];

        default:
            return state;
    }
};

export default CartReducer;
