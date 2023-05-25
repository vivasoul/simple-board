import {useQuasar} from "quasar";


export function useValidation() {
    const $q = useQuasar()

    return {
        boardValidator({title, catNo, content, passwd}, isNew) {

            const _catNo = catNo.value;
            let chk = false;
            if (!title) {
                $q.notify({
                    type: "negative",
                    message: "제목을 입력해주세요."
                })
            } else if (_catNo === null || catNo < 1) {
                $q.notify({
                    type: "negative",
                    message: "카테고리를 선택해주세요."
                })
            } else if (!content) {
                $q.notify({
                    type: "negative",
                    message: "내용을 입력해주세요."
                })
            } else if(isNew && !passwd){
                $q.notify({
                    type: "negative",
                    message: "수정 비밀번호를 입력해주세요."
                })
            } else {
                chk = true
            }

            return chk
        }
    }
}