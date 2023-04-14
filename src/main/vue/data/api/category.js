import axios from "axios"

export async function getCategories() {
    const res = await axios.get("/categories")

    if(res.status == 200) {
        return res.data
    } else {
        return res.statusText
    }
}