/**
 * OpenAPI definition
 * No description provided (generated by Openapi Generator https://github.com/openapitools/openapi-generator)
 *
 * The version of the OpenAPI document: v0
 * 
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
import { GrantedAuthority } from './grantedAuthority';


export interface User { 
    id?: number;
    fullName?: string;
    email?: string;
    password?: string;
    createdAt?: string;
    updatedAt?: string;
    enabled?: boolean;
    accountNonLocked?: boolean;
    accountNonExpired?: boolean;
    credentialsNonExpired?: boolean;
    authorities?: Array<GrantedAuthority>;
    username?: string;
}
