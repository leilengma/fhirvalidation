package com.fgu.fhir.validation;

import java.io.IOException;

import org.hl7.fhir.common.hapi.validation.support.CommonCodeSystemsTerminologyService;
import org.hl7.fhir.common.hapi.validation.support.InMemoryTerminologyServerValidationSupport;
import org.hl7.fhir.common.hapi.validation.support.NpmPackageValidationSupport;
import org.hl7.fhir.common.hapi.validation.support.ValidationSupportChain;
import org.hl7.fhir.common.hapi.validation.validator.FhirInstanceValidator;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.context.support.DefaultProfileValidationSupport;
import ca.uhn.fhir.validation.FhirValidator;

public class Main {
    public static final String bundle ="""
{
    "resourceType": "Bundle",
    "id": "f9609469-5246-4aa6-8ac9-4fdc9c921793",
    "type": "transaction",
    "entry": [
        {
            "fullUrl": "urn:uuid:d5dd51ca-113f-40fc-b60c-cf44546bd961",
            "resource": {
                "resourceType": "Location",
                "id": "d5dd51ca-113f-40fc-b60c-cf44546bd961",
                "meta": {
                    "profile": [
                        "http://bbraun.de/fhir/infusion/StructureDefinition/GPlusLocation"
                    ]
                },
                "identifier": [
                    {
                        "use": "official",
                        "system": "http://gp-hospital.de/fhir/sid/unit",
                        "value": "gp_unit_ICU"
                    }
                ],
                "name": "Station 3 East",
                "physicalType": {
                    "coding": [
                        {
                            "system": "http://terminology.hl7.org/CodeSystem/location-physical-type",
                            "code": "wa"
                        }
                    ]
                }
            }
        },
        {
            "fullUrl": "urn:uuid:30abbd97-27d0-45ac-a700-9f66e16b867d",
            "resource": {
                "resourceType": "Patient",
                "id": "30abbd97-27d0-45ac-a700-9f66e16b867d",
                "meta": {
                    "profile": [
                        "http://bbraun.de/fhir/infusion/StructureDefinition/GPlusPatient"
                    ]
                },
                "identifier": [
                    {
                        "use": "official",
                        "system": "http://gp-hospital.de/fhir/sid/patient",
                        "value": "pw-test-patient-ICU"
                    }
                ],
                "name": [
                    {
                        "family": "Fischer",
                        "given": [
                            "David"
                        ]
                    }
                ],
                "gender": "male",
                "birthDate": "1960-06-10"
            }
        },
        {
            "fullUrl": "urn:uuid:282375d2-1854-46da-9b59-89236ebb7bc8",
            "resource": {
                "resourceType": "Encounter",
                "id": "282375d2-1854-46da-9b59-89236ebb7bc8",
                "meta": {
                    "profile": [
                        "http://bbraun.de/fhir/infusion/StructureDefinition/GPlusEncounter"
                    ]
                },
                "identifier": [
                    {
                        "use": "official",
                        "system": "http://gp-hospital.de/fhir/sid/encounter",
                        "value": "pw-test-encounter-ICU"
                    }
                ],
                "status": "in-progress",
                "class": {
                    "system": "http://terminology.hl7.org/CodeSystem/v3-ActCode",
                    "code": "IMP"
                },
                "subject": {
                    "reference": "urn:uuid:30abbd97-27d0-45ac-a700-9f66e16b867d"
                },
                "period": {
                    "start": "2025-11-24T09:00:00+00:00",
                    "end": "2025-11-24T10:30:00+00:00"
                },
                "location": [
                    {
                        "location": {
                            "reference": "urn:uuid:d5dd51ca-113f-40fc-b60c-cf44546bd961"
                        },
                        "status": "active",
                        "physicalType": {
                            "coding": [
                                {
                                    "system": "http://terminology.hl7.org/CodeSystem/location-physical-type",
                                    "code": "wa"
                                }
                            ]
                        }
                    },
                    {
                        "location": {
                            "display": "room 3"
                        },
                        "status": "active",
                        "physicalType": {
                            "coding": [
                                {
                                    "system": "http://terminology.hl7.org/CodeSystem/location-physical-type",
                                    "code": "ro"
                                }
                            ]
                        }
                    },
                    {
                        "location": {
                            "display": "bed 2"
                        },
                        "status": "active",
                        "physicalType": {
                            "coding": [
                                {
                                    "system": "http://terminology.hl7.org/CodeSystem/location-physical-type",
                                    "code": "bd"
                                }
                            ]
                        }
                    }
                ]
            }
        },
        {
            "fullUrl": "urn:uuid:469e1c84-0c1c-4b9c-aaa6-72c352e0e799",
            "resource": {
                "resourceType": "Medication",
                "id": "469e1c84-0c1c-4b9c-aaa6-72c352e0e799",
                "meta": {
                    "profile": [
                        "http://bbraun.de/fhir/infusion/StructureDefinition/GPlusMedication"
                    ]
                },
                "code": {
                    "coding": [
                        {
                            "system": "http://www.nlm.nih.gov/research/umls/rxnorm",
                            "code": "1049630",
                            "display": "Paracetamol 500mg Tablet"
                        }
                    ]
                },
                "form": {
                    "coding": [
                        {
                            "system": "http://standardterms.edqm.eu",
                            "code": "385055001",
                            "display": "Tablet dose form (qualifier value)"
                        }
                    ]
                },
                "amount": {
                    "numerator": {
                        "value": 500,
                        "unit": "mg",
                        "system": "http://unitsofmeasure.org",
                        "code": "mg"
                    },
                    "denominator": {
                        "value": 1,
                        "unit": "g",
                        "system": "http://unitsofmeasure.org",
                        "code": "g"
                    }
                },
                "ingredient": [
                    {
                        "itemCodeableConcept": {
                            "coding": [
                                {
                                    "system": "http://www.nlm.nih.gov/research/umls/rxnorm",
                                    "code": "161",
                                    "display": "Paracetamol"
                                }
                            ]
                        },
                        "isActive": true
                    },
                    {
                        "itemCodeableConcept": {
                            "coding": [
                                {
                                    "system": "http://www.nlm.nih.gov/research/umls/rxnorm",
                                    "code": "161",
                                    "display": "Paracetamol"
                                }
                            ]
                        },
                        "isActive": false
                    }
                ]
            }
        },
        {
            "fullUrl": "urn:uuid:835860cd-8ee9-4fbc-b71a-70c259ed19fd",
            "resource": {
                "resourceType": "Practitioner",
                "id": "835860cd-8ee9-4fbc-b71a-70c259ed19fd",
                "meta": {
                    "profile": [
                        "http://bbraun.de/fhir/infusion/StructureDefinition/GPlusPractitioner"
                    ]
                },
                "identifier": [
                    {
                        "use": "official",
                        "system": "http://gp-hospital.de/fhir/sid/practitioner",
                        "value": "pw-test-practitioner-ICU"
                    }
                ],
                "name": [
                    {
                        "family": "Smith",
                        "given": [
                            "John"
                        ],
                        "prefix": [
                            "Dr."
                        ]
                    }
                ]
            }
        },
        {
            "fullUrl": "urn:uuid:e9990964-0a33-4ce6-893a-096c0c384168",
            "resource": {
                "resourceType": "MedicationRequest",
                "id": "e9990964-0a33-4ce6-893a-096c0c384168",
                "meta": {
                    "profile": [
                        "http://bbraun.de/fhir/infusion/StructureDefinition/GPlusMedicationRequest"
                    ]
                },
                "extension": [
                    {
                        "url": "http://hl7.org/fhir/StructureDefinition/timezone",
                        "valueCode": "Europe/Berlin"
                    }
                ],
                "identifier": [
                    {
                        "use": "official",
                        "system": "http://gp-hospital.de/fhir/sid/medicationrequest",
                        "value": "pw-test-medicationrequest-ICU"
                    }
                ],
                "status": "active",
                "intent": "order",
                "medicationReference": {
                    "reference": "urn:uuid:469e1c84-0c1c-4b9c-aaa6-72c352e0e799"
                },
                "subject": {
                    "reference": "urn:uuid:30abbd97-27d0-45ac-a700-9f66e16b867d"
                },
                "encounter": {
                    "reference": "urn:uuid:282375d2-1854-46da-9b59-89236ebb7bc8"
                },
                "authoredOn": "2025-11-24T10:00:00Z",
                "requester": {
                    "reference": "urn:uuid:835860cd-8ee9-4fbc-b71a-70c259ed19fd"
                },
                "dosageInstruction": [
                    {
                        "text": "Take one tablet by mouth every 6 hours as needed for pain",
                        "timing": {},
                        "asNeededBoolean": true,
                        "route": {
                            "coding": [
                                {
                                    "system": "http://standardterms.edqm.eu",
                                    "code": "20045000",
                                    "display": "Intravenous use"
                                }
                            ]
                        },
                        "doseAndRate": [
                            {
                                "doseQuantity": {
                                    "value": 100,
                                    "unit": "mL",
                                    "system": "http://unitsofmeasure.org",
                                    "code": "mL"
                                },
                                "rateQuantity": {
                                    "value": 200,
                                    "unit": "mL/h",
                                    "system": "http://unitsofmeasure.org",
                                    "code": "mL/h"
                                }
                            }
                        ]
                    }
                ]
            }
        }
    ]
}            
            """;

    public static void main(String[] args) throws IOException {
        var ctx = FhirContext.forR4();

        NpmPackageValidationSupport npmPackageSupport = new NpmPackageValidationSupport(ctx);
        npmPackageSupport.loadPackageFromClasspath("classpath:ig/fhir-ig-gplus-0.2.0.tgz");

        ValidationSupportChain coreChain = new ValidationSupportChain(
            npmPackageSupport,
            new DefaultProfileValidationSupport(ctx),
            new InMemoryTerminologyServerValidationSupport(ctx),
            new CommonCodeSystemsTerminologyService(ctx));

        VersionedUrlFallbackValidationSupport versionedUrlFallback =
            new VersionedUrlFallbackValidationSupport(ctx, coreChain);

        ValidationSupportChain validationSupportChain = new ValidationSupportChain(
            versionedUrlFallback,
            coreChain);

        FhirValidator validator = ctx.newValidator();
        FhirInstanceValidator instanceValidator = new FhirInstanceValidator(validationSupportChain);
        validator.registerValidatorModule(instanceValidator);
        instanceValidator.setAnyExtensionsAllowed(true);
        validator.validateWithResult(bundle).getMessages().forEach(m ->{
            System.out.println(m.getLocationString()); 
            System.out.println(m.getSeverity()); 
            System.out.println(m.getMessage());
            System.out.println("-----" );
        });
    }
}
