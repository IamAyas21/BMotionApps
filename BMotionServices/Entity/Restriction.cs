
//------------------------------------------------------------------------------
// <auto-generated>
//     This code was generated from a template.
//
//     Manual changes to this file may cause unexpected behavior in your application.
//     Manual changes to this file will be overwritten if the code is regenerated.
// </auto-generated>
//------------------------------------------------------------------------------


namespace BMotionServices.Entity
{

using System;
    using System.Collections.Generic;
    
public partial class Restriction
{

    public int RestrictionId { get; set; }

    public Nullable<int> MenuId { get; set; }

    public Nullable<int> RoleId { get; set; }

    public Nullable<bool> Read { get; set; }

    public Nullable<bool> Add { get; set; }

    public Nullable<bool> Update { get; set; }

    public Nullable<bool> Delete { get; set; }



    public virtual Menu Menu { get; set; }

    public virtual Role Role { get; set; }

}

}
