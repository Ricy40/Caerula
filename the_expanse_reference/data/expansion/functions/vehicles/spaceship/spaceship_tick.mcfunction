# run when the player is inside the spaceship
execute if entity @s[tag=inside_spaceship] at @s run function expansion:vehicles/spaceship/inside

# displays the options menu when the player sneaks near the spaceship
execute if entity @s[predicate=expansion:utility/sneak,tag=!inside_spaceship] unless score @s optn_cooldown matches 1.. if entity @e[type=armor_stand,tag=exp_spaceship,distance=..2] run function expansion:vehicles/spaceship/options

# runs functions depending on the option chosen
execute if entity @s[scores={spaceship_optns=1}] unless entity @s[predicate=expansion:nbt_checks/armor/space_equipment/equipment] run function expansion:handy_tools/error_messages/equip_space_equipment
execute if entity @s[scores={spaceship_optns=1},predicate=expansion:nbt_checks/armor/space_equipment/equipment] if entity @e[type=armor_stand,tag=exp_spaceship,limit=1,sort=nearest,distance=..3] run function expansion:vehicles/spaceship/enter
execute if entity @s[scores={spaceship_optns=2},tag=!inside_spaceship] as @e[type=armor_stand,tag=exp_spaceship,limit=1,sort=nearest,distance=..5] at @s run function expansion:vehicles/spaceship/fuel
execute if entity @s[scores={spaceship_optns=3}] as @e[type=armor_stand,tag=exp_spaceship,limit=1,sort=nearest,distance=..3] at @s run function expansion:vehicles/spaceship/demount

# reset options scoreboards
scoreboard players set @s[scores={spaceship_optns=1..}] spaceship_optns 0
scoreboard players set @s[scores={demount_optns=1..}] demount_optns 0
scoreboard players remove @s[scores={optn_cooldown=1..}] optn_cooldown 1