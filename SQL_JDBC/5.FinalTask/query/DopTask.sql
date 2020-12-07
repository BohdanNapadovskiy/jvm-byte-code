SELECT person.id, person.name, company.id, company.name FROM person AS person 
LEFT JOIN company ON person.company_id = company.id
WHERE person.company_id <> 5;

SELECT company.id, company.name, COUNT(person.name)as person_count, MAX(person.id)
FROM company AS company
LEFT JOIN person AS person ON (company.id = person.company_id)
GROUP BY company.name,company.id
ORDER BY company.id;
