import axios from "axios"

export async function getReply(brdNo) {
    const res = await axios.get(`/board/${brdNo}/reply`)

    if(res.status == 200) {
        return  res.data
    } else {
        return null
    }
}

export async function createReply({brdNo, content}) {
    const res = await axios.post(`/board/${brdNo}/reply`, {brdNo, content})

    if(res.status == 200) {
        return true;
    } else {
        return false;
    }
}

export async function updateReply({brdNo, replNo, content}) {
    const res = await axios.put(`/board/${brdNo}/reply/${replNo}`, {brdNo, replNo, content})

    if(res.status == 200) {
        return true;
    } else {
        return false;
    }
}

export async function deleteReply(brdNo, replNo) {
    const res = await axios.delete(`/board/${brdNo}/reply/${replNo}`)

    if(res.status == 200) {
        return true;
    } else {
        return false;
    }
}