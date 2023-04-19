import {useQuasar} from "quasar";


export function useValidation() {
    const $q = useQuasar()

    return {
        boardValidator({title, catNo, content}) {
            let chk = false;
            if (!title) {
                $q.notify({
                    type: "negative",
                    message: "제목을 입력해주세요."
                })
            } else if (!catNo) {
                $q.notify({
                    type: "negative",
                    message: "카테고리를 선택해주세요."
                })
            } else if (!content) {
                $q.notify({
                    type: "negative",
                    message: "내용을 입력해주세요."
                })
            } else {
                chk = true
            }

            return chk
        }
    }
}