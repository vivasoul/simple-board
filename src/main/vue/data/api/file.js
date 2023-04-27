import axios from "axios";
export async function uploadFiles(postData) {
    try {
        const res = await axios.post("/files/upload", postData)

        if (res.status == 200) {
            return res.data
        } else {
            return res.statusText
        }
    }catch(e) {
        console.log(e)
    }
}

export async function deleteFile(fileId) {
    try {
        const res = await axios.delete(`/files/${fileId}`)

        if (res.status == 200) {
            return true
        } else {
            console.log(res.status+" : "+res.statusText)
            return false
        }
    }catch(e) {
        console.log(e)
    }
}