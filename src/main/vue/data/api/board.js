import axios from "axios"

export async function getBoard(catNo) {
    const res = await axios.get(`/board?catNo=${catNo || ''}`)

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

export async function createBoard({title, content, catNos, files}) {
    const res = await axios.post("/board", {title, content, catNos, files})

    if(res.status == 200) {
        return true;
    } else {
        return false;
    }
}

export async function updateBoard({brdNo, title, content, catNos, files}) {
    const res = await axios.put(`/board/${brdNo}`, {brdNo, title, content, catNos, files})

    if(res.status == 200) {
        return true;
    } else {
        return false;
    }
}

export async function deleteBoard(brdNo) {
    const res = await axios.delete(`/board/${brdNo}`)

    if(res.status == 200) {
        return true;
    } else {
        return false;
    }
}