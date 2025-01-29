INSERT INTO incorrect_text (wrong_text, correction_id, category)
VALUES('결제', 1, 'business')
;

INSERT INTO correction_dictionary (alias, corrected_text, category, priority, description)
VALUES('결재', '결재', 'business', 0, '결정할 권한이 있는 선임자가 후임자의 제출 안건을 검토하여 허가하거나 승인함')
;