import axios from "axios"

export async function getBoard() {
    const res = await axios.get("/board")

    if(res.status == 200) {
        return  res.data
    } else {
        return null
    }
}

export async function getBoardDetail(brdNo) {
    const res = await axios.get(`/board/${brdNo}`)

    if(res.status == 200) {
        return  res.data
    } else {
        return null
    }
}